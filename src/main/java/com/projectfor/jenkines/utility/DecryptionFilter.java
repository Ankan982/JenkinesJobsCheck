
package com.projectfor.jenkines.utility;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class DecryptionFilter extends OncePerRequestFilter {
	
	 @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain filterChain)
	            throws ServletException, IOException 
	 {
		 
		 String path = request.getRequestURI();

		  

	        if ("POST".equalsIgnoreCase(request.getMethod())
	                && request.getRequestURI().equals("/api/V1/window/postData")) {

	        	String body = new BufferedReader(new InputStreamReader(request.getInputStream()))
	                    .lines().reduce("", (acc, line) -> acc + line);

	            try {
	                String decryptedBody = CryptoUtil.decrypt(body);

	                HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(request) {
	                    @Override
	                    public ServletInputStream getInputStream() throws IOException {
	                        final ByteArrayInputStream byteArrayInputStream =
	                                new ByteArrayInputStream(decryptedBody.getBytes());
	                        return new ServletInputStream() {
	                            public int read() throws IOException {
	                                return byteArrayInputStream.read();
	                            }

	                            public boolean isFinished() {
	                                return byteArrayInputStream.available() == 0;
	                            }

	                            public boolean isReady() {
	                                return true;
	                            }

	                           
								@Override
								public void setReadListener(ReadListener listener) {
									// TODO Auto-generated method stub
									
								}
	                        };
	                    }

	                    @Override
	                    public BufferedReader getReader() throws IOException {
	                        return new BufferedReader(new InputStreamReader(this.getInputStream()));
	                    }
	                };

	                filterChain.doFilter(wrappedRequest, response);
	            } catch (Exception e) {
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid encrypted data");
	            }

	        } else {
	            filterChain.doFilter(request, response);
	        }
	    }

}

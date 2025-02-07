package com.revising.apidev.configuration;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream capture;
    private final ServletOutputStream outputStream;
    private final PrintWriter writer;

    public CustomHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
        capture = new ByteArrayOutputStream();
        outputStream = new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            public void setReadListener(WriteListener listener) {
            }

            @Override
            public void write(int b) throws IOException {
                capture.write(b);
            }
        };
        writer = new PrintWriter(outputStream, true);
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        super.flushBuffer();
        writer.flush();
        outputStream.flush();
    }

    public byte[] getCapturedResponse() {
        return capture.toByteArray();
    }
}


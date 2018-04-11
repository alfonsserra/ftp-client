package com.systelab;


import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.nio.charset.StandardCharsets;


public final class RemoteFTP {

    public void connectAndSendFiles() {

        FTPClient client = new FTPClient();

        try {
            client.connect("ftp.domain.com");
            client.login("admin", "secret");
            sendImageFile(client,"image.png");
            sendJSONFile(client);
            client.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendImageFile(FTPClient client, String filename) {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(filename);
            client.storeFile(filename, fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendJSONFile(FTPClient client) {
        InputStream is = getJSonFile();
        String filename = "destinationFile.json";
        try {
            client.storeFile(filename, is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public InputStream getJSonFile() {
        String content = "{\"pattern\": \"" + "homogenous, speckled, centromere, nucleolar" + "\"}";
        InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        return stream;
    }

    public static void main(String[] args) {
        new RemoteFTP().connectAndSendFiles();
    }
}

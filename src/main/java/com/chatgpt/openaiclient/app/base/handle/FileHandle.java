package com.chatgpt.openaiclient.app.base.handle;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileHandle {

    public static String getKey() {
        StringBuilder stringBuilder = new StringBuilder();
        String userHome = System.getProperty("user.home");
        String documentsPath = userHome + File.separator + "Documents";
        File targetDir = new File(documentsPath, "OPENAI_KEY");
        File targetFile = new File(targetDir, "key.conf");

        try {
            if (!targetDir.exists()) {
                boolean dirCreated = targetDir.mkdirs();
                if (dirCreated) {
                    System.out.println("目录已创建：" + targetDir.getAbsolutePath());
                } else {
                    System.err.println("目录创建失败：" + targetDir.getAbsolutePath());
                }
            }

            if (!targetFile.exists()) {
                boolean fileCreated = targetFile.createNewFile();
                if (fileCreated) {
                    System.out.println("文件已创建：" + targetFile.getAbsolutePath());
                    try (FileWriter writer = new FileWriter(targetFile)) {
                        writer.write("your--key");
                    }
                } else {
                    System.err.println("文件创建失败：" + targetFile.getAbsolutePath());
                }
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(targetFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }

        } catch (IOException e) {
            log.debug(e.getMessage());
        }
        return stringBuilder.toString();
    }

}

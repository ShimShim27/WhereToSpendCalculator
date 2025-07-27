package wts.calc.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {
    public static String readFile(final String path){
        final File f = new File(path);
        try {
            final BufferedReader bf = new BufferedReader(new FileReader(f));
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bf.readLine()) != null) {
                sb.append(line.trim());
            }
            bf.close();
            return sb.toString().trim();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

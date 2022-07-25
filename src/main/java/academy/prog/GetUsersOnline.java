package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetUsersOnline {
    private final Gson gson;

    public GetUsersOnline() {
        gson = new GsonBuilder().create();
    }

    public void getPresentUsers() throws IOException {
        URL url = new URL(Utils.getURL() + "/users-online");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        try (InputStream is = http.getInputStream()) {
            byte[] buf = responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            System.out.println("Online users:");
            System.out.println(strBuf);
        }
    }

    private byte[] responseBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;
        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);
        return bos.toByteArray();
    }
}

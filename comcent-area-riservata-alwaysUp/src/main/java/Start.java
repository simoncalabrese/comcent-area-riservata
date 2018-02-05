import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Start {
    public static void main(String[] args) throws InterruptedException, IOException {
        while (true) {
            long millis = System.currentTimeMillis();
            final String basePath = "https://comunicationcenter.herokuapp.com/util/getDocs";

            final URL url = new URL(basePath);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            final DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write("".getBytes());
            dataOutputStream.flush();
            dataOutputStream.close();
            System.out.println(connection.getInputStream());
            Thread.sleep(3600000 - millis % 3600000);
        }

    }
}

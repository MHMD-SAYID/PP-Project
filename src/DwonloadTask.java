import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class DownloadTask implements Runnable {
    private final String fileUrl;

    public DownloadTask(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public void run() {
        try {
            downloadFile(fileUrl);
        } catch (Exception e) {
            System.err.println("Error downloading: " + fileUrl + " -> " + e.getMessage());
        }
    }

    private void downloadFile(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

        try (InputStream in = url.openStream();
             FileOutputStream fos = new FileOutputStream(fileName)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("Downloaded: " + fileName);
        }
    }
}

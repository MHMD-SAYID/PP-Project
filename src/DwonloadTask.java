import java.io.*;
import java.net.URL;
import java.nio.file.Paths;

class DownloadTask implements Runnable {
    private final String fileUrl;
    private static final String DOWNLOAD_FOLDER = "downloads/";

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

        // Extract file name safely
        String fileName = Paths.get(url.getPath()).getFileName().toString();

        // Remove invalid characters
        fileName = fileName.replaceAll("[^a-zA-Z0-9.\\-_]", "_"); // Replaces invalid chars with "_"

        // Ensure the download directory exists
        File dir = new File(DOWNLOAD_FOLDER);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(DOWNLOAD_FOLDER + fileName);
        try (InputStream in = url.openStream();
             FileOutputStream fos = new FileOutputStream(file)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("Downloaded: " + file.getAbsolutePath());
        }
    }
}

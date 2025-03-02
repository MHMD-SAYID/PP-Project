/*import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class FileDownloader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the URL
        System.out.print("Enter the file URL to download: ");
        String fileUrl = scanner.nextLine();

        // Ask the user for the download location
        System.out.print("Enter the directory to save the file: ");
        String saveDirectory = scanner.nextLine();

        // Extract the file name from URL
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

        // Create the full path
        String savePath = saveDirectory + File.separator + fileName;

        // Start download
        downloadFile(fileUrl, savePath);

        scanner.close();
    }

    public static void downloadFile(String fileUrl, String savePath) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }

            System.out.println("Download completed: " + savePath);
        } catch (IOException e) {
            System.out.println("Error downloading file: " + e.getMessage());
        }
    }
}*/

 import java.io.*;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileDownloader {
    public static void main(String[] args) {
        // List of files to download (URLs)
        String[] fileUrls = {
                "https://i.etsystatic.com/8073388/r/il/8e53f8/806908565/il_1588xN.806908565_lyd0.jpg",
                "https://st3.depositphotos.com/5585086/35069/i/1600/depositphotos_350692254-stock-photo-dog-beagle-running-fast-jumping.jpg",
                "https://i.pinimg.com/736x/38/8e/12/388e122827b4d5979f3b5d8c517a1076.jpg"
                 };

        // Thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (String fileUrl : fileUrls) {
            executor.execute(new DownloadTask(fileUrl));
        }

        executor.shutdown(); // Shutdown the executor after all tasks finish
    }
}


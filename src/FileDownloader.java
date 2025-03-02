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

package com.example.exploration.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ReadFiles {
  private final Queue<File> fileQueue = new ArrayDeque<>();
  private final Object queueLock = new Object();

  public static void main(String[] args) throws URISyntaxException, IOException {
    //
    ReadFiles readFiles = new ReadFiles();

    Path files = Path.of(ReadFiles.class.getClassLoader().getResource("files").toURI());

    for (File file : Objects.requireNonNull(files.toFile().listFiles())) {
      readFiles.add(file);
    }

    //

    Path dest = Path.of(files.getParent().toString(), "dest");
    log.info("dest: " + dest.toString());
    if (!dest.toFile().exists()) {
      log.info("create file");
      Files.createDirectories(dest);
    }

    ExecutorService executor = Executors.newFixedThreadPool(5); // creating a pool of 5 threads
    for (int i = 0; i < 10; i++) {
      executor.execute(
          () -> {
            File pop = readFiles.pop();
            if (pop == null) {
              return;
            } else {
              log.info(Thread.currentThread().getName() + " read: " + pop.getName());
            }

            Path destFile = Path.of(dest.toString(), pop.getName());
            try (InputStream in = new FileInputStream(pop);
                OutputStream out = new FileOutputStream(destFile.toFile())) {
              IOUtils.copy(in, out);
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            } catch (IOException e) {
              e.printStackTrace();
            }
          });
    }
    executor.shutdown();

    while (!executor.isTerminated()) {
      // main thread waiting
    }

    log.info("Finished all threads");
  }

  public void add(File file) {
    synchronized (queueLock) {
      log.info("add file: " + file.getName());
      fileQueue.add(file);
    }
  }

  public File pop() {
    synchronized (queueLock) {
      return fileQueue.poll();
    }
  }
}

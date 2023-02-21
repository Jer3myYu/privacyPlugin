package com.cmu.privacyplugin;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TerminalCommand {
    public class StandardIo {
        BufferedReader stdout, stderr;

        public StandardIo(InputStream stdoutStream, InputStream stderrStream) {
            this.stdout = new BufferedReader(new InputStreamReader(stdoutStream));
            this.stderr = new BufferedReader(new InputStreamReader(stderrStream));
        }
    }

    public TerminalCommand() {}

    public StandardIo runCommand(String[] cmdArray) {
        try {
            // Create the process builder and set the command
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(cmdArray);

            // Start the process
            Process process = processBuilder.start();

            // Get the output of the command
            StandardIo standardIo = new StandardIo(process.getInputStream(), process.getErrorStream());

            // Wait for the command to complete
            int exitCode = process.waitFor();
            System.out.println("Command exited with code " + exitCode);
            return standardIo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printTerminal(BufferedReader reader) {
        try{
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
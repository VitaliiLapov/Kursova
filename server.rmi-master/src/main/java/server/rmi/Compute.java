package server.rmi;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {

    public static final String RMI_SERVER_NAME = "lpi.server.rmi";

    public void ping() throws RemoteException;

    public String echo(String text) throws RemoteException;

    <T> T executeTask(Task<T> t) throws RemoteException, ArgumentException, ServerException;

    public long timeProcesMethod(selectionSort selectionSort) throws RemoteException;

    public interface Task<T> {
        T execute();
    }

    public static class selectionSort implements Task<byte[]>, Serializable {
        private static final long serialVersionUID = 227L;

        private String nameRandomFile;
        private String nameSortFile;
        private byte[] fileRandom;
        private byte[] fileSort;
        private Integer[] intMasForSort;
        public static long startAlgoritm, finishAlgoritm, timeConsumedMilis;

        public selectionSort() {
        }

        public selectionSort(String nameSortFile, File file) throws IOException {
            this.nameSortFile = nameSortFile;
            this.nameRandomFile = file.getName();
            this.fileRandom = Files.readAllBytes(file.toPath());
            parsInfoFromFileRandomIntoMas(fileRandom);
        }

        public selectionSort(String nameRandomFile, String nameSortFile, byte[] fileRandom) {
            this.nameRandomFile = nameRandomFile;
            this.nameSortFile = nameSortFile;
            this.fileRandom = fileRandom;
            parsInfoFromFileRandomIntoMas(fileRandom);
        }

        private Integer[] parsInfoFromFileRandomIntoMas(byte[] file) {
            String infoFromFile = new String(file);
            String[] numInStingType = infoFromFile.split(" ");
            intMasForSort = new Integer[numInStingType.length];

            for (int i = 0; i < numInStingType.length; i++) {
                intMasForSort[i] = Integer.parseInt(numInStingType[i]);
            }
            return intMasForSort;
        }

        public static void selectionSort(Integer[] arr) {
            for (int min = 0; min < arr.length - 1; min++) {
                int least = min;
                for (int j = min + 1; j < arr.length; j++) {
                    if (arr[j] < arr[least]) {
                        least = j;
                    }
                }
                int tmp = arr[min];
                arr[min] = arr[least];
                arr[least] = tmp;
            }
        }

        @Override
        public byte[] execute() {
            startAlgoritm = System.nanoTime();
            selectionSort(intMasForSort);
            finishAlgoritm = System.nanoTime();
            timeConsumedMilis = finishAlgoritm - startAlgoritm;
            setTimeConsumedMilis(timeConsumedMilis);

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < intMasForSort.length; i++) {
                if (i-1 != intMasForSort.length ) {
                    stringBuilder.append(intMasForSort[i] + " ");
                } else {
                    stringBuilder.append(intMasForSort[i]);
                }
            }
            return stringBuilder.toString().getBytes();
        }

        public void setTimeConsumedMilis(long timeConsumedMilis) {
            this.timeConsumedMilis = timeConsumedMilis;
        }

        public String getNameRandomFile() {
            return nameRandomFile;
        }

        public void setNameRandomFile(String nameRandomFile) {
            this.nameRandomFile = nameRandomFile;
        }

        public String getNameSortFile() {
            return nameSortFile;
        }

        public void setNameSortFile(String nameSortFile) {
            this.nameSortFile = nameSortFile;
        }

        public byte[] getFileRandom() {
            return fileRandom;
        }

        public void setFileRandom(byte[] fileRandom) {
            this.fileRandom = fileRandom;
        }

        public byte[] getFileSort() {
            return fileSort;
        }

        public void setFileSort(byte[] fileSort) {
            this.fileSort = fileSort;
        }

        public long getStartAlgoritm() {
            return startAlgoritm;
        }

        public long getFinishAlgoritm() {
            return finishAlgoritm;
        }

        public long getTimeConsumedMilis() {
            return timeConsumedMilis;
        }
    }

    public static class Sum implements Task<Integer>, Serializable {
        private static final long serialVersionUID = 228L;

        private final Integer a;
        private final Integer b;

        public Sum(Integer a, Integer b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Integer execute() {
            return a + b;
        }
    }

    public static class ServerException extends RemoteException {
        private static final long serialVersionUID = 2592458695363000913L;

        public ServerException() {
            super();
        }

        public ServerException(String message, Throwable cause) {
            super(message, cause);
        }

        public ServerException(String message) {
            super(message);
        }
    }

    public static class ArgumentException extends RemoteException {
        private static final long serialVersionUID = 8404607085051949404L;

        private String argumentName;

        public ArgumentException() {
            super();
        }

        public ArgumentException(String argumentName, String message, Throwable cause) {
            super(message, cause);
            this.argumentName = argumentName;
        }

        public ArgumentException(String argumentName, String message) {
            super(message);
            this.argumentName = argumentName;
        }

        public String getArgumentName() {
            return argumentName;
        }
    }

}

package processor;

import java.util.Scanner;

public class Matrix {
    public static final String ENTER_XXX_SIZE = "Enter size of %s matrix:\n";
    public static final String ENTER_XXX_MATRIX = "Enter %s matrix:\n";
    public static final String RESULT_IS = "The result is:";
    public static final String ENTER_CONSTANT = "Enter constant:";
    public static final String ERROR = "The operation cannot be performed.";


    double matrix[][]; // Matrix
    int rows; // Rows in matrix
    int column; // Column in Matrix
    boolean isMatrixExist = false;
    String userInput;
    String[] userInputArray;

    Matrix(Scanner scanner, int numberOfMatrix) {
        if (numberOfMatrix == 1) {
            System.out.printf(ENTER_XXX_SIZE, "first");
        } else if (numberOfMatrix == 2) {
            System.out.printf(ENTER_XXX_SIZE, "second");
        }

        userInput = scanner.nextLine();
        userInputArray = userInput.trim().split(" ");
        rows = Integer.parseInt(userInputArray[0]);
        column = Integer.parseInt(userInputArray[1]);
        matrix = new double[rows][column];
        isMatrixExist = true;

        if (numberOfMatrix == 1) {
            System.out.printf(ENTER_XXX_MATRIX, "first");
        } else if (numberOfMatrix == 2) {
            System.out.printf(ENTER_XXX_MATRIX, "second");
        }

        for (int x = 0; x < rows; x++) {
            userInput = scanner.nextLine();
            userInputArray = userInput.trim().split(" ");
            for (int y = 0; y < column; y++) {
                matrix[x][y] = Double.parseDouble(userInputArray[y]);
            }
        }
    }

    public Matrix(int rows, int column) {
        matrix = new double[rows][column];
    }

    Matrix(Scanner scanner, int numberOfMatrix, int firstRow, int firstColumn) {
        if (numberOfMatrix == 1) {
            System.out.printf(ENTER_XXX_SIZE, "first");
        } else if (numberOfMatrix == 2) {
            System.out.printf(ENTER_XXX_SIZE, "second");
        }

        userInput = scanner.nextLine();
        userInputArray = userInput.trim().split(" ");
        rows = Integer.parseInt(userInputArray[0]);
        column = Integer.parseInt(userInputArray[1]);

        if (rows != firstRow && column != firstColumn) {
            System.out.println(ERROR);
            return;
        }


        matrix = new double[rows][column];
        isMatrixExist = true;

        if (numberOfMatrix == 1) {
            System.out.printf(ENTER_XXX_MATRIX, "first");
        } else if (numberOfMatrix == 2) {
            System.out.printf(ENTER_XXX_MATRIX, "second");
        }

        for (int x = 0; x < rows; x++) {
            userInput = scanner.nextLine();
            userInputArray = userInput.trim().split(" ");
            for (int y = 0; y < column; y++) {
                matrix[x][y] = Double.parseDouble(userInputArray[y]);
            }
        }
    }


    public int getRows() {
        return matrix.length;
    }

    public boolean isMatrixExist() {
        return isMatrixExist;
    }

    public int getColumn() {
        return matrix[0].length;
    }

    public double getElement(int x, int y) {
        return matrix[x][y];
    }

    public void setElement(int x, int y, double element) {
        matrix[x][y] = element;
    }

    public void printSumOfMatrix(Matrix matrix1, Matrix matrix2) {
        System.out.println(RESULT_IS);
        for (int x = 0; x < matrix1.getRows(); x++) {
            for (int y = 0; y < matrix1.getColumn(); y++) {
                System.out.printf("%.2f ", matrix1.getElement(x,y) + matrix2.getElement(x,y));
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printOneMatrix(Matrix matrix) {
        System.out.println(RESULT_IS);
        for (int x = 0; x < matrix.getRows(); x++) {
            for (int y = 0; y < matrix.getColumn(); y++) {
                System.out.printf("%f ", matrix.getElement(x,y));
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printMultiplyOnConstant(Scanner scanner, Matrix matrix1) {
        System.out.println(ENTER_CONSTANT);
        userInput = scanner.nextLine();
        userInputArray = userInput.trim().split(" ");
        int multiplicator = Integer.parseInt(userInputArray[0]);
        for (int x = 0; x < matrix1.getRows(); x++) {
            for (int y = 0; y < matrix1.getColumn(); y++) {
                matrix1.setElement(x,y,matrix1.getElement(x,y) * multiplicator);
            }
        }
        printOneMatrix(matrix1);
    }

    public void printMultiplicationOfMatrix(Matrix matrix1, Matrix matrix2, Matrix matrix3) {
        for (int x = 0; x < matrix3.getRows(); x++) {
            for (int y = 0; y < matrix3.getColumn(); y++) {
                double elementResult = 0;
                for (int k = 0; k < matrix1.getColumn(); k++) {
                    //System.out.printf("x: %d y: %d 1: %.2f 2: %.2f\n", x,y,matrix1.getElement(x,k),matrix2.getElement(k,y));
                    elementResult += matrix1.getElement(x,k) * matrix2.getElement(k,y);
                }
                matrix[x][y] = elementResult;
            }
        }
        printOneMatrix(matrix3);
    }

    public void printTranspose(Matrix matrix, int userChoise) {
        Matrix matrix2 = new Matrix(matrix.getRows(), matrix.getColumn());
        switch (userChoise) {
            case 1: {
                transposeMainDiagonal(matrix, matrix2);
                break;
            }
            case 2: {
                transposeSideDiagonal(matrix, matrix2);
                break;
            }
            case 3: {
                transposeVerticalLine(matrix, matrix2);
                break;
            }
            case 4: {
                transposeHorizontalLine(matrix, matrix2);
                break;
            }
        }
        printOneMatrix(matrix2);
    }

    private void transposeSideDiagonal(Matrix matrix, Matrix matrix2) {
        for (int x = 0; x < matrix.getRows(); x++) {
            for (int y = 0; y < matrix.getColumn(); y++) {
                double a = matrix.getElement(matrix.getColumn() - (y + 1), matrix.getRows() - (x + 1));
                matrix2.setElement(x,y,a);
            }
        }
    }

    private void transposeMainDiagonal(Matrix matrix, Matrix matrix2) {
        for (int x = 0; x < matrix.getRows(); x++) {
            for (int y = 0; y < matrix.getColumn(); y++) {
                matrix2.setElement(x,y,matrix.getElement(y,x));
            }
        }
    }

    private void transposeVerticalLine(Matrix matrix, Matrix matrix2) {
        for (int x = 0; x < matrix.getRows(); x++) {
            for (int y = 0; y < matrix.getColumn(); y++) {
                matrix2.setElement(x,matrix.getColumn() - y - 1,matrix.getElement(x,y));
            }
        }
    }

    private void transposeHorizontalLine(Matrix matrix, Matrix matrix2) {
        for (int x = 0; x < matrix.getRows(); x++) {
            for (int y = 0; y < matrix.getColumn(); y++) {
                matrix2.setElement(matrix.getRows() - x - 1,y,matrix.getElement(x,y));
            }
        }
    }
}
//

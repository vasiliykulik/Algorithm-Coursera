import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Vasiliy Kylik on 08.10.2017.
 * Hint: At minimum, you'll need to store the grid size,
 * which sites are open, and which sites are connected to which other sites.
 * The last of these is exactly what the union-find data structure is designed for.
 */
public class Percolation {

    private int rowSize;
    private int gridSize;
    private boolean[] grid;
    private WeightedQuickUnionUF quickUnionUF;
    private int bottom;
    private int top;
    private int num;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input error n must be > 0 " + n);
        }
        rowSize = n;
        gridSize = n * n;
        grid = new boolean[gridSize];
        bottom = gridSize + 1;
        top = gridSize;
        quickUnionUF = new WeightedQuickUnionUF(gridSize + 2);
        num = 0;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > rowSize) {
            throw new IllegalArgumentException("Input error : row index out of bounds 0 - " + rowSize);
        }
        if (col < 1 || col > rowSize) {
            throw new IllegalArgumentException("Input error : col index out of bounds 0 - " + rowSize);
        }
    }

    private int xyTo1D(int row, int col) {
        return (row - 1) * rowSize + (col - 1);
    }


    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int index = xyTo1D(row, col);
        num++;
        grid[index] = true;
        if (row == 1) {
            quickUnionUF.union(index, top);
        }
        boolean hasNext = false;
        for (int i = 0; i < 4; i++) {
            int adjustment = getAdjustment(row, col, i);
            if (adjustment != -1) {
                quickUnionUF.union(adjustment, index);
                hasNext = true;
            }
        }
        if (hasNext) {
            for (int index2 = gridSize - 1; index2 >= gridSize - rowSize; index2--) {
                if (grid[index2] && quickUnionUF.connected(top, index2)) {
                    quickUnionUF.union(index2, bottom);
                    break;
                }
            }
        }
    }

    private int getAdjustment(int row, int col, int i) {
        int rowRow = 0;
        int colCol = 0;
        int adjustment = -1;
        if (i == 0) {
            rowRow = row - 1;
            colCol = col;
        } else if (i == 1) {
            rowRow = row + 1;
            colCol = col;
        } else if (i == 2) {
            rowRow = row;
            colCol = col - 1;
        } else if (i == 3) {
            rowRow = row;
            colCol = col + 1;
        } else {
            throw new java.lang.IllegalArgumentException(
                    " var i must have direction 0up, 1down, 2left, 3right");
        }
        if (rowRow > 0 && rowRow <= rowSize && colCol > 0 && colCol <= rowSize) {
            if (isOpen(rowRow, colCol)) {
                adjustment = xyTo1D(rowRow, colCol);
            }
        }
        return adjustment;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[xyTo1D(row, col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return quickUnionUF.connected(top, xyTo1D(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return num;
    }

    // does the system percolate?
    public boolean percolates() {
        return quickUnionUF.connected(top, bottom);
    }


    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);
        System.out.println("Bottom root position " + percolation.bottom);
        System.out.println("Percolation? " + percolation.percolates());
        System.out.println("Grid Length " + percolation.grid.length);
        percolation.open(4, 1);
        percolation.open(3, 1);
        percolation.open(2, 1);
        percolation.open(1, 1);
        percolation.open(1, 4);
        percolation.open(2, 4);
        System.out.println(percolation.quickUnionUF.connected(16, 8));
        percolation.open(4, 4);
        System.out.println(percolation.quickUnionUF.find(3));
        System.out.println("Percolation ? " + percolation.percolates());
    }
}


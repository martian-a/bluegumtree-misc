package uk.co.bluegumtree.code.java.util;

import java.util.ArrayList;

/**
 * 
 * For creating, formatting and presenting tabular content.
 */
public class Table {

	public enum Align {
		LEFT, RIGHT, CENTRE;
	}

	private String[][] table;
	private int totalHeaderRows;
	private int totalFooterRows;
	private String title;

	/**
	 * Constructor to convert current table to have zero header rows, columns
	 * and footer
	 * 
	 * @param currTable
	 */
	public Table(ArrayList<String[]> currTable) {
		this(Table.convert(currTable), 0, 0, 0);
	}

	/**
	 * Constructor to convert current table to have current header rows, and
	 * zero columns and footer
	 * 
	 * @param currTable
	 */
	public Table(ArrayList<String[]> currTable, int currHeaderRows) {
		this(Table.convert(currTable), currHeaderRows, 0, 0);
	}

	/**
	 * Constructor to convert current table to have current header rows and
	 * columns
	 * 
	 * @param currTable
	 * - the current table
	 * @param currHeaderRows
	 * - the current header rows
	 * @param currHeaderColumns
	 * - the current header columns
	 */
	public Table(ArrayList<String[]> currTable, int currHeaderRows, int currHeaderColumns) {
		this(Table.convert(currTable), currHeaderRows, currHeaderColumns, 0);
	}

	/**
	 * Constructor to convert current table to have current header rows, columns
	 * and footer
	 * 
	 * @param currTable
	 * - the current table
	 * @param currHeaderRows
	 * - the current header rows
	 * @param currHeaderColumns
	 * - the current header columns
	 * @param currFooterRows
	 * - the current footer rows
	 */
	public Table(ArrayList<String[]> currTable, int currHeaderRows, int currHeaderColumns, int currFooterRows) {
		this(Table.convert(currTable), currHeaderRows, currHeaderColumns, currFooterRows);
	}

	/**
	 * Constructor to create a table as an double array with no header rows,
	 * column or footer rows
	 * 
	 * @param currTable
	 */
	public Table(String[][] currTable) {
		this(currTable, 0, 0, 0);
	}

	/**
	 * Constructor to create a table as a double array with a header row, but no
	 * column header or footer rows
	 * 
	 * @param currTable
	 * - the current table
	 * @param currHeaderRows
	 * - current header rows
	 */
	public Table(String[][] currTable, int currHeaderRows) {
		this(currTable, currHeaderRows, 0, 0);
	}

	/**
	 * Constructor to create a table as a double array with a header row, and
	 * header columns but no footer rows
	 * 
	 * @param currTable
	 * - the current table
	 * @param currHeaderRows
	 * - current header rows
	 * @param currHeaderColumns
	 * - current header columns
	 */
	public Table(String[][] currTable, int currHeaderRows, int currHeaderColumns) {
		this(currTable, currHeaderRows, currHeaderColumns, 0);
	}

	/**
	 * Constructor to create a table as a double array with a header row, header
	 * columns and footer rows
	 * 
	 * @param currTable
	 * - the current table
	 * @param currHeaderRows
	 * - current header rows
	 * @param currHeaderColumns
	 * - current header columns
	 * @param currFooterRows
	 * - current footer rows
	 * 
	 */
	public Table(String[][] currTable, int currHeaderRows, int currHeaderColumns, int currFooterRows) {
		this.table = currTable;
		this.totalHeaderRows = currHeaderRows;
		this.totalFooterRows = currFooterRows;
	}

	/**
	 * format - formats table and aligns the columns and rows
	 * 
	 * @param options
	 */
	public void format(Align[] options) {

		for (String[] currRow : this.table) {
			for (String currCell : currRow) {
				if (currCell != null) {
					currCell = currCell.trim();
				} else {
					currCell = "";
				}
			}
		}

		// Get the dimensions of the Table
		int totalColumns = this.getTotalColumns();
		int totalRows = this.getTotalRows();
		// Format the contents of each of the columns
		// in the Table
		for (int c = 0; c < totalColumns; c++) {

			// Gather together all the cells in the current column
			String[] currColumn = new String[totalRows];
			for (int r = 0; r < totalRows; r++) {
				currColumn[r] = this.table[r][c];
			}

			// Measure the width of the widest cell in this column
			int columnWidth = Formatter.getLengthOfLongestString(currColumn);

			// Loop through all the cells in this column,
			// making them all the same width as the widest cell,
			// aligning the contents to the right and
			// adding padding to either side of each cell
			for (int r = 0; r < totalRows; r++) {
				String currCell = "";
				if (options[c] == Align.RIGHT) {
					currCell = Formatter.alignRight(currColumn[r], columnWidth);
				} else if (options[c] == Align.CENTRE) {
					currCell = Formatter.alignCentre(currColumn[r], columnWidth);
				} else {
					currCell = Formatter.alignLeft(currColumn[r], columnWidth);
				}
				this.table[r][c] = " " + currCell + " ";
			}

		}

	}

	/**
	 * getHeight gets the height of the table
	 * 
	 * @return height of table (no of rows)
	 */
	public int getHeight() {
		return this.getTotalRows();
	}

	/**
	 * getTitle
	 * 
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * getTotalColumns
	 * 
	 * @return the length of the table number of columns
	 */
	public int getTotalColumns() {
		return this.table[0].length;
	}

	/**
	 * getTotalFooterRows
	 * 
	 * @return totalFooterRows in table
	 */
	private int getTotalFooterRows() {
		return this.totalFooterRows;
	}

	/**
	 * getTotalHeaderRows
	 * 
	 * @return totalHeaderRows in table
	 */
	private int getTotalHeaderRows() {
		return this.totalHeaderRows;
	}

	/**
	 * getTotalRows
	 * 
	 * @return number of rows for table
	 */
	public int getTotalRows() {
		return this.table.length;
	}

	/**
	 * getWidth determines number of characters wide
	 * 
	 * @return totalCharacters
	 */
	public int getWidth() {

		int totalCharacters = 0;
		for (String currCell : this.table[0]) {
			totalCharacters = totalCharacters + currCell.length();
		}
		return totalCharacters;
	}

	/**
	 * hasFooter
	 * 
	 * @return true if has and false if not
	 */
	private boolean hasFooter() {
		if (this.totalFooterRows > 0) {
			return true;
		}
		return false;
	}

	/**
	 * hasHeader
	 * 
	 * @return true if table has a header and false otherwise
	 */
	private boolean hasHeader() {
		if (this.totalHeaderRows > 0) {
			return true;
		}
		return false;
	}

	/**
	 * setTitle
	 * 
	 * @param currTitle
	 * - current title required for table instance
	 */
	public void setTitle(String currTitle) {
		this.title = currTitle;
	}

	/**
	 * toString method generates table characters
	 * 
	 * @return a table
	 */
	@Override
	public String toString() {

		String Table = "";
		int totalRows = this.getTotalRows();
		int totalColumns = this.getTotalColumns();
		int lastColumnIndex = totalColumns - 1;
		int lastHeaderRowIndex = this.getTotalHeaderRows() - 1;
		int lastContentRowIndex = totalRows - this.getTotalFooterRows() - 1;
		int totalWidth = this.getWidth() + totalColumns - 1;
		boolean hasHeader = this.hasHeader();
		boolean hasFooter = this.hasFooter();
		String leftMargin = Formatter.copy(" ", 5);

		if (this.getTitle() != null) {
			Table = Table + leftMargin + this.getTitle() + "\n\n";
		}
		for (int r = 0; r < totalRows; r++) {
			String[] currRow = this.table[r];
			for (int c = 0; c < totalColumns; c++) {
				String currCell = currRow[c];
				if (c == 0) {
					currCell = leftMargin + currCell;
				}
				if (c != lastColumnIndex) {
					currCell = currCell + "\u250A";
				}
				Table = Table + currCell;
			}
			Table = Table + "\n";
			if ((hasHeader && r == lastHeaderRowIndex) || (hasFooter && r == lastContentRowIndex)) {
				Table = Table + leftMargin + Formatter.copy("\u2504", totalWidth) + "\n";
			}
		}

		return Table;
	}

	/**
	 * Converts a table array list into a 2-dimensional array.
	 * 
	 * @param currTable
	 * an ArrayList of 1-dimensional String arrays.
	 * @return a 2-dimensional array
	 */
	protected static String[][] convert(ArrayList<String[]> currTable) {

		int totalRows = currTable.size();
		int totalColumns = currTable.get(0).length;
		String[][] TableAsArray = new String[totalRows][totalColumns];

		for (int rowIndex = 0; rowIndex < totalRows; rowIndex++) {
			String[] currRow = currTable.get(rowIndex);
			for (int colIndex = 0; colIndex < totalColumns; colIndex++) {
				TableAsArray[rowIndex][colIndex] = currRow[colIndex];
			}
		}

		return TableAsArray;
	}

}
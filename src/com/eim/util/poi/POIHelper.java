package com.eim.util.poi;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

public class POIHelper {

	static Pattern CELL_PATTERN = Pattern.compile("([A-Z]+)(\\d+)");
	private static Logger log = Logger.getLogger(POIHelper.class);
	
	/**
	 * Get cell as indicate as in Excel (A1, B3, etc.)
	 * @param sheet the given sheet
	 * @param cell the cell name e.g A1 Z20 AA65
	 * @return the given cell of null if does not exist
	 */
	public static Cell getCell(Sheet sheet, String cell) {
		
		String _cell = cell.toUpperCase();
		Matcher m = CELL_PATTERN.matcher(_cell);
		
		if (m.matches() == false)
			return null;
		
		int row = Integer.parseInt(m.group(2)) - 1;
		String column = m.group(1);
		int _column = 0;
		
		int nbChar = column.length();
		// column in upper case to compute char
		for (char c: column.toUpperCase().toCharArray()) {
			// get column
			_column += (Character.getNumericValue(c) - 10) + 26 * (nbChar - 1);
			nbChar--;
		}
		
		return sheet.getRow(row).getCell(_column);
	}
	
	/**
	 * Get range of cell as array
	 * @param sheet the given sheet
	 * @param fromCell the cell from which to start the range
	 * @param rows the number of rows for the range. Must be positive. 1 means only current (fromCell) row
	 * @param columns the number of columns for the range. Must be positive. 1 means only current (fromCell) column
	 * @return an array of cells
	 */
	public static Cell[][] getRange(Sheet sheet, Cell fromCell, int rows, int columns) {
		assert rows > 0 : "rows parameter must be positive";
		assert columns > 0 : "columns parameter must be positive";
		
		Cell[][] result = new Cell[rows][columns];
		
		int currentRow = fromCell.getRowIndex();
		int currentColumn = fromCell.getColumnIndex();
		
		// loop on rows and columns
		for (int iRow = 0; iRow < rows; iRow++) {
			for (int iCol = 0; iCol < columns; iCol++) {
				result[iRow][iCol] = sheet.getRow(currentRow + iRow).getCell(currentColumn + iCol);
			}
		}
		
		return result;
	}
	
	/**
	 * Test wether a cell is null or not. Null is empty for String cells and 0 for numeric cells .
	 * @param cell the given cell
	 * @return true or false
	 */
	public static boolean isCellNull(Cell cell) {
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				String stringValue = cell.getStringCellValue().trim();
				return stringValue.isEmpty();
			case Cell.CELL_TYPE_NUMERIC:
				double numericValue = cell.getNumericCellValue();
				return numericValue == 0;
			default:
				return true;
		}
	}
	
	/**
	 * Get long value from cell (troncate any decimal). Throws Exception if type error
	 * @param cell the given cell
	 * @return Long or null if cell is empty
	 */
	public static Long getLongCellValue(Cell cell) {
		if (cell == null)
			return null;
		
		// cell can be blank
		if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
			return null;
		
		return new Double(cell.getNumericCellValue()).longValue();
	}
	
	/**
	 * Get string value from cell. Throws Exception if type error
	 * @param cell the given cell
	 * @return String or null if cell is empty
	 */
	public static String getStringCellValue(Cell cell) {
		if (cell == null)
			return null;
		
		String result = cell.getStringCellValue().trim();
		if (result.isEmpty())
			return null;
		return result;
	}
	
	/**
	 * Get date value from cell. Throws Exception if type error 
	 * @param cell the given cell
	 * @return Date or null if cell is empty
	 */
	public static Date getDateCellValue(Cell cell) {
		if (cell == null)
			return null;
		
		try {
			return cell.getDateCellValue();
		} catch (IllegalStateException ex) {
			log.error(cell.toString() + " does not have a proper date format", ex);
			throw ex;
		}
	}
	
}
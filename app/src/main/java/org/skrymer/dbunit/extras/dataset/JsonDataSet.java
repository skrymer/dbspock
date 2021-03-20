package org.skrymer.dbunit.extras.dataset;

import org.dbunit.dataset.*;

import java.nio.file.Path;

/**
 *
 * <pre>
 * [
 *   TableName1: {
 *     "column1": "value",
 *     "column2": "value",
 *     "column3": "value",
 *     "column4": "value",
 *     "column5": "value"
 *   },
 *   TableName2: {
 *     "column1": "value",
 *     "column2": 1234,
 *     "column3": true,
 *     "column4": "value",
 *     "column5": "value"
 *   }
 * ]
 * <pre/>
 *
 */
public class JsonDataSet implements IDataSet {

  public JsonDataSet(Path jsonFile){

  }

  @Override
  public String[] getTableNames() throws DataSetException {
    return new String[0];
  }

  @Override
  public ITableMetaData getTableMetaData(String tableName) throws DataSetException {
    return null;
  }

  @Override
  public ITable getTable(String tableName) throws DataSetException {
    return null;
  }

  @Override
  public ITable[] getTables() throws DataSetException {
    return new ITable[0];
  }

  @Override
  public ITableIterator iterator() throws DataSetException {
    return null;
  }

  @Override
  public ITableIterator reverseIterator() throws DataSetException {
    return null;
  }

  @Override
  public boolean isCaseSensitiveTableNames() {
    return false;
  }
}

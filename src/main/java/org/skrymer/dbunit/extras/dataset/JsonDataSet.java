package org.skrymer.dbunit.extras.dataset;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dbunit.dataset.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <pre>
 * [
 *   {
 *     "Table1" : [
 *       {"column1": "value1", "column2": "value2", "column3": "value3"},
 *       {"column1": "value1", "column2": "value2", "column3": "value3"},
 *       {"column1": "value1", "column2": "value2", "column3": "value3"}
 *     ]
 *   },
 *   {
 *     "Table2" : [
 *       {"column1": "{REPLACEMENT1}::default1", "column2": "111"}
 *     ]
 *   }
 * ]
 * <pre/>
 */
public class JsonDataSet extends AbstractDataSet {
  private final Path jsonFile;
  private final Map<String, String> stringReplacements;
  private final ObjectMapper objectMapper;

  public JsonDataSet(Path jsonFile) {
    this(jsonFile, null);
  }

  public JsonDataSet(Path jsonFile, Map<String, String> stringReplacements) {
    this.jsonFile = jsonFile;
    this.stringReplacements = stringReplacements;
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public String[] getTableNames() throws DataSetException {
    try {
      var tableNames = new ArrayList<String>();
      objectMapper
          .readTree(jsonFile.toFile())
          .forEach(node -> node
              .fields()
              .forEachRemaining(stringJsonNodeEntry -> tableNames.add(stringJsonNodeEntry.getKey()))
          );
      return tableNames.toArray(new String[0]);
    } catch (IOException e) {
      throw new DataSetException("Could not load tables names from dataset");
    }
  }

  @Override
  public ITable getTable(String tableName) throws DataSetException {
    try {
      var columns = objectMapper
          .readTree(jsonFile.toFile())
          .findPath(tableName);
      return new JsonTable(columns, stringReplacements);
    } catch (IOException e) {
      throw new DataSetException("Could not load tables names from dataset");
    }
  }

  @Override
  public ITable[] getTables() throws DataSetException {
    return Arrays.stream(getTableNames())
        .map(tableName -> {
          try {
            return getTable(tableName);
          } catch (DataSetException e) {
            return null;
          }
        })
        .collect(Collectors.toList())
        .toArray(new ITable[]{});
  }

  @Override
  public boolean isCaseSensitiveTableNames() {
    return false;
  }

  @Override
  protected ITableIterator createIterator(boolean reversed) throws DataSetException {
    return new DefaultTableIterator(getTables(), reversed);
  }
}

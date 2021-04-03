package org.skrymer.dbunit.extras.dataset;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableMetaData;

import java.util.Collections;
import java.util.Map;

import static java.util.Optional.ofNullable;

public class JsonTable implements ITable {
  private static final String DEFAULT_VALUE_PREFIX = "::";
  private final ArrayNode columns;
  private final Map<String, String> stringReplacements;

  public JsonTable(JsonNode columns, Map<String, String> stringReplacements) {
    if (!(columns instanceof ArrayNode)) {
      throw new IllegalArgumentException("columns should be an array");
    }
    this.columns = (ArrayNode) columns;
    this.stringReplacements = stringReplacements != null
        ? stringReplacements
        : Collections.emptyMap();
  }

  @Override
  public ITableMetaData getTableMetaData() {
    return null;
  }

  @Override
  public int getRowCount() {
    return columns.size();
  }

  @Override
  public Object getValue(int row, String column) {
    var value = columns.get(0).findValue(column).asText();

    return isPlaceholder(value) ? replaceValue(value) : value;
  }

  private boolean isPlaceholder(String value) {
    return ofNullable(value).map(s -> s.matches("\\{.*}.*")).orElse(false);
  }

  private Object replaceValue(String value) {
    var hasDefaultValue = hasDefaultValue(value);
    var replacedValue = hasDefaultValue
        ? stringReplacements.get(value.substring(0, value.indexOf(DEFAULT_VALUE_PREFIX)))
        : stringReplacements.get(value);

    return replacedValue == null && hasDefaultValue
      ? getDefaultValue(value)
      : replacedValue;
  }

  private boolean hasDefaultValue(String value){
    return ofNullable(value)
        .map(s -> s.matches("\\{.*}" + DEFAULT_VALUE_PREFIX + ".*"))
        .orElse(false);
  }

  private Object getDefaultValue(String value) {
    return value.substring(value.indexOf(DEFAULT_VALUE_PREFIX) + DEFAULT_VALUE_PREFIX.length());
  }
}

package org.skrymer.dbunit.extras.spock;

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;
import org.spockframework.runtime.model.SpecInfo;

public class DBUnitConfigAnnotationDrivenExtension extends AbstractAnnotationDrivenExtension<DBUnitConfig> {

  @Override
  public void visitSpecAnnotation(DBUnitConfig annotation, SpecInfo spec) {
//    super.visitSpecAnnotation(annotation, spec);
    System.setProperty("dbunit.dataSetLocation", annotation.dataSetLocation());
  }
}

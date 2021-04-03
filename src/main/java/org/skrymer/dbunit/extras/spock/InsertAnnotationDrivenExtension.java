package org.skrymer.dbunit.extras.spock;

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;
import org.spockframework.runtime.model.FeatureInfo;
import org.spockframework.runtime.model.MethodInfo;

public class InsertAnnotationDrivenExtension extends AbstractAnnotationDrivenExtension<Insert> {

  @Override
  public void visitFeatureAnnotation(Insert annotation, FeatureInfo feature) {
    //    feature.addInterceptor(); // intercetor to insert
  }

  @Override
  public void visitFixtureAnnotation(Insert annotation, MethodInfo fixtureMethod) {
//    feature.addInterceptor(); // intercetor to insert
  }
}

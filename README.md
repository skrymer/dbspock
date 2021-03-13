# dbspock

Inspired by the features off DBUnit

Requirements:

insert data 

assert db state

Dataset format:
Use json as dataformat.
Allow 

```groovy
@DBSpock(datasetLocations = '')
class Spec extends Specification {

  @Shared
  Connection connection

  
  def '' () {
    insert()
  }
}
```

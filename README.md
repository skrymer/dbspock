# dbunit-extras

## Json Dataset

### Expressions in dataset

### Default values in dataset
```json
[
  {
    "Table" : [
      {"column1": "{REPLACEMENT1}::default1", "column2": "123"}
    ]
  }
]
```

## Spock integration

###Annotation driven dbunit config 
```groovy
@DBUnitConfig({
    dataSetLocation = "src/main/test/resources"
})
class Spec extends Specification {
    
}
```


## TODO

### Logging
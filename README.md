The processor allows processing of CSV files. The following properties are to be configured in application.properties file:
  a. csv.separator: the separator character of the CSV file
  b. csv.file: CSV file to be read/parsed to a list of Objects. Typically this can be supplied via command line or GUI.
  c. csv.file.create: the CSV file created as a result of the converse processor.
  d. csv.processor.error.continue: true or false to indicative to continue or terminate program on encountering an error.
  e. csv.file.test.read.write: used to test read and write processor with already pre-determined list of objects.

For any CSV file, it is required to create a corresponding POJO similar to the one in the project CsvRecord. 
Apply appropriate annotations to the fields of the object and call the reader to parse the CSV file to a list of objects.

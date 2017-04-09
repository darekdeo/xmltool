# xmltool
xmltool to merge translation files for S.T.A.L.K.E.R

Usage:
1. build jar or download release from: https://github.com/darekdeo/xmltool/releases/tag/1
2. run program in console: java -jar xmltool.jar source_file.xml file_to_be_merged_to_source_file.xml
3. program should generate merged_output.xml in the same directory where jar program is located

note: program may show error if .xml file is badly formatted, like "The reference to entity "H" must end with the ';' delimiter..." together with line of error. Most likely "&" character is used in the source file, this character must be changed to "\&amp;" since it is the correct format for xml. When editing the .xml file error in text editor make sure to use proper encoding (windows-1251)!

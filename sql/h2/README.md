To test sql with h2 in the terminal (not related to molecule, just for separate H2 query testing):

download h2 at https://www.h2database.com/html/main.html

    cd h2/bin
    java -cp h2-2.2.224.jar org.h2.tools.Shell  // (change to current version)
    <enter>
    <enter>
    <enter>
    <enter>

Then you can start entering H2 sql queries

sql> select 1;
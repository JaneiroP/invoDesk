@echo off
echo Starting InvoDesk Sequential Execution...
echo =====================================

cd /d "C:\Users\janeiro.placido\Desktop\taza\invoDesk"

echo.
echo Checking JAR files...
echo ---------------------
set LOMBOK_JAR=.\lib\lombok.jar
set SQLITE_JAR=.\lib\sqlite-jdbc-3.49.1.0.jar

if not exist "%LOMBOK_JAR%" (
    echo ERROR: Lombok JAR not found at %LOMBOK_JAR%
    pause
    exit /b 1
)

if not exist "%SQLITE_JAR%" (
    echo ERROR: SQLite JAR not found at %SQLITE_JAR%
    echo Please download sqlite-jdbc jar to lib folder
    pause
    exit /b 1
)

set CLASSPATH=%LOMBOK_JAR%;%SQLITE_JAR%

echo Lombok JAR: %LOMBOK_JAR% [OK]
echo SQLite JAR: %SQLITE_JAR% [OK]
echo Combined Classpath: %CLASSPATH%

echo.
echo Step 2: Updating sources.txt...
powershell -Command "Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName } | Set-Content sources.txt"

echo.
echo Step 3: Compiling...
javac -cp "%CLASSPATH%" @sources.txt
if %errorlevel% neq 0 (
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)

echo.
echo Step 4: Running SQLite test...
java -cp "%CLASSPATH%;." db.SQLiteConexion

pause
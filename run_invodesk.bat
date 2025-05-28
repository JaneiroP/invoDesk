@echo off
echo Starting InvoDesk Sequential Execution...
echo =====================================

REM Change to the project directory
cd /d "C:\Users\janeiro.placido\Desktop\taza\invoDesk"

echo.
echo Searching for Lombok jar...
echo --------------------------
\util\lombok.jar
REM Check for Lombok in common locations
set LOMBOK_JAR=
if exist ".\util\lombok.jar" (
    set LOMBOK_JAR=.\util\lombok.jar
    echo Found Lombok at: .\util\lombok.jar
) else if exist "..\lib\lombok.jar" (
    set LOMBOK_JAR=..\lib\lombok.jar
    echo Found Lombok at: ..\lib\lombok.jar
) else if exist "lib\lombok.jar" (
    set LOMBOK_JAR=lib\lombok.jar
    echo Found Lombok at: lib\lombok.jar
) else if exist "..\lombok.jar" (
    set LOMBOK_JAR=..\lombok.jar
    echo Found Lombok at: ..\lombok.jar
) else if exist "lombok.jar" (
    set LOMBOK_JAR=lombok.jar
    echo Found Lombok at: lombok.jar
) else (
    echo ERROR: Lombok jar not found!
    echo Please download lombok.jar from https://projectlombok.org/download
    echo and place it in one of these locations:
    echo   - ..\util\lombok.jar
    echo   - ..\lib\lombok.jar
    echo   - lib\lombok.jar
    pause
    exit /b 1
)

echo.
echo Step 1: Initial compilation with existing sources...
echo --------------------------------------------------
javac -cp "%LOMBOK_JAR%" @sources.txt
if %errorlevel% neq 0 (
    echo ERROR: Initial compilation failed!
    echo.
    echo Possible solutions:
    echo 1. Download lombok.jar from https://projectlombok.org/download
    echo 2. Place it in ..\util\ folder
    echo 3. Or remove Lombok annotations from your Java files
    pause
    exit /b 1
)
echo Initial compilation successful!

echo.
echo Step 2: Updating sources.txt with all Java files...
echo --------------------------------------------------
powershell -Command "Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName } | Set-Content sources.txt"
if %errorlevel% neq 0 (
    echo ERROR: Updating sources.txt failed!
    pause
    exit /b 1
)
echo Sources.txt updated successfully!

echo.
echo Step 3: Recompiling with updated sources...
echo ------------------------------------------
javac -cp "%LOMBOK_JAR%" @sources.txt
if %errorlevel% neq 0 (
    echo ERROR: Second compilation failed!
    pause
    exit /b 1
)
echo Second compilation successful!

echo.
echo Step 4: Running Main class...
echo ----------------------------
java -cp "%LOMBOK_JAR%;." Main
if %errorlevel% neq 0 (
    echo ERROR: Main execution failed!
    echo Check if Main class exists and has a main method
    pause
    exit /b 1
)

echo.
echo =====================================
echo All steps completed successfully!
echo =====================================
pause
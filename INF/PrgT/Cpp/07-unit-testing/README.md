# Unit Testing

The main goal of this Work Sheet is to apply unit tests in C++.
* Get to know a selection of frameworks
* Dive deeper into TDD using Boost.Test

## Preparations for Boost.Test

### Linux
For Debian based distributions, install the package `libboost-test-dev`.

#### Windows
Under Windows the steps are a bit more complicated.   
Most Boost libraries are header-only, but `Boost.Graph`, `Boost.Math`, `Boost.Random`, `Boost.Test` (unit test framework) and `Boost.Exception` are not built by default.

1. Download the library from Boost (https://www.boost.org/users/download/) and unpack it to a directory of your liking. 

2. Compile the library for your system.
   In the directory execute the following PowerShell commands in the Windows terminal, which will take some time to complete:
   ```powershell
   .\bootstrap.bat
   .\b2 install              
   .\b2 --with-test install  #(only compile test)
   ```
   The library will be installed to `C:\Boost`.

3. Add the path to your Boost folder (C:\Boost) via an environment variable called `BOOST_ROOT`.

### Adding Boost to CMake
1. Read the instructions [FindBoost](https://cmake.org/cmake/help/latest/module/FindBoost.html) to add Boost to CMake. 
To add it with the minimum required Boost version of `1.74` in the root `CMakeLists.txt', add:
```
   find_package(Boost 1.74.0)
   include_directories(${Boost_INCLUDE_DIRS})
```
2. After adding the respective command to the root `CMakeLists.txt`,
   reload / save your project and see if CMake can find the library.


## Evaluation of unit testing frameworks for C++
There are various frameworks that can be used for unit testing with C++.
The following list shows a subset,
and the goal of this exercise is to get an overview of how those are
integrated and what the benefits and drawbacks are.
This is a group exercise.
Be prepared to present and share your result afterwards.
Include web links wherever possible.

### Exercise questions:
1. What kind of framework is it (monolithic or modular)?
   Can you find and include into your presentation a graphical overview of the frameworks architecture?
2. How to install it in case you use VS Code or other IDE, demonstrate it there.
3. What is the basic structure of a test file?
4. What linguistic elements introduce a test?
5. Create a C++ method `multiply()` which multiplies 2 integers and returns the result.
   Show the following test scenarios in a demo project:
   * Multiply two positive numbers
   * Multiply with a negative number
   * Multiply with 0

### Frameworks:
1. Google Test:
   * Where to find Google Test: https://github.com/google/googletest
   * Online example for your reference:
     https://developer.ibm.com/technologies/systems/articles/au-googletestingframework/
2. Catch²:
   * Where to find Catch2: https://github.com/catchorg/Catch2
   * Online example for your reference:
     https://mariusbancila.ro/blog/2018/03/29/writing-cpp-unittests-with-catch2/
3. Doctest:
   * Test environment included into CLION
   * https://blog.jetbrains.com/clion/2020/06/doctest-in-clion/
4. Boost Unit Test Framework: preferrably for those who would like to catch up on previous Boost.Tests
   * Where to find Boost and Boost.Test: https://www.boost.org/users/download/
   * Online example for your reference: https://www.ibm.com/developerworks/aix/library/auctools1_boost/index.html

### Exercise workflow:
1. You’ll be assigned to one of the frameworks into a breakout room.
2. about 20 minutes: as a team ... try to answer the given questions 1-4
3. about 25 minutes: prepare question 5 in form of a live demo.
4. about 5  minutes: merge your results into a presentation. Agree on a presenter.
5. about 10 minutes for each team: present your findings and give the life demo.

## Unit Test for the class Student
Given is an incomplete test file `student_tests.cpp`.
Fill in the sections marked with TODO.

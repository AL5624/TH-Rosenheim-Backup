if(EXISTS "C:/Users/Anton/Documents/Fh-Rosenheim/TH-Rosenheim-Backup/INF/PrgT/Cpp/01-cpp-introduction/tests/polynomial_tests/polynomial_tests[1]_tests.cmake")
  include("C:/Users/Anton/Documents/Fh-Rosenheim/TH-Rosenheim-Backup/INF/PrgT/Cpp/01-cpp-introduction/tests/polynomial_tests/polynomial_tests[1]_tests.cmake")
else()
  add_test(polynomial_tests_NOT_BUILT polynomial_tests_NOT_BUILT)
endif()

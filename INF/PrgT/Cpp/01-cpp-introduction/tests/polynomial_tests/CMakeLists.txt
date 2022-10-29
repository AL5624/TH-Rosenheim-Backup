cmake_minimum_required(VERSION 3.18)
project(polynomial_tests)

add_executable(${PROJECT_NAME} polynomial_tests.cpp)
target_link_libraries(${PROJECT_NAME} libpolynomial gtest gtest_main)

set(Labels polynomial-tests)
gtest_discover_tests(
        ${PROJECT_NAME}
        PROPERTIES LABELS "${Labels}"
        XML_OUTPUT_DIR reports
)

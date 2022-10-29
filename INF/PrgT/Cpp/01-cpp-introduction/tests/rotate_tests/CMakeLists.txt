cmake_minimum_required(VERSION 3.18)
project(rotate_tests)

add_executable(${PROJECT_NAME} rotate_tests.cpp)
target_link_libraries(${PROJECT_NAME} librotate gtest gtest_main)

set(Labels rotate-tests)
gtest_discover_tests(
        ${PROJECT_NAME}
        PROPERTIES LABELS "${Labels}"
        XML_OUTPUT_DIR reports
)

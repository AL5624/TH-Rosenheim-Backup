project(multiply_tests)

add_executable(${PROJECT_NAME} MultiplyTests.cpp)
target_link_libraries(${PROJECT_NAME} lib_multiply gtest gtest_main)

set(Labels multiply-test)
gtest_discover_tests(
        ${PROJECT_NAME}
        PROPERTIES LABELS "${Labels}"
        XML_OUTPUT_DIR reports
)
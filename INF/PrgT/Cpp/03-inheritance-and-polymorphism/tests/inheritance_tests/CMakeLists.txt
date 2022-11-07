project(inheritance_tests)

add_executable(${PROJECT_NAME} shape_tests.cpp circle_tests.cpp)
target_link_libraries(${PROJECT_NAME} lib_inheritance gtest gtest_main)

set(Labels inheritance-tests)
gtest_discover_tests(
        ${PROJECT_NAME}
        PROPERTIES LABELS "${Labels}"
        XML_OUTPUT_DIR reports
)
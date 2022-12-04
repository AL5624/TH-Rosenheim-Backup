project(catch2_multiply_tests)

add_executable(${PROJECT_NAME} multiply_tests.cpp)
target_link_libraries(${PROJECT_NAME} lib_multiply Catch2::Catch2)

set(Labels multiply-catch2)
catch_discover_tests(
        ${PROJECT_NAME}
        PROPERTIES
        LABELS "${Labels}"
        REPORTER junit
        OUTPUT_DIR reports
        OUTPUT_SUFFIX .xml
)
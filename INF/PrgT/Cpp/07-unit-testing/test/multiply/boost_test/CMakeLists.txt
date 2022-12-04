project(boost_test_multiply_tests)

set (Boost_USE_STATIC_LIBS OFF)


include_directories (${Boost_INCLUDE_DIRS})

add_executable(${PROJECT_NAME} multiply_tests.cpp)
target_link_libraries(${PROJECT_NAME} lib_multiply Boost::unit_test_framework)

set(Labels multiply-boost.test)
add_test(
        NAME
        Boost.Test-Multiply
        COMMAND
        ${PROJECT_NAME}
        --log_format=JUNIT
        --log_sink=${PROJECT_NAME}.xml
)
set_tests_properties(
        Boost.Test-Multiply
        PROPERTIES
        LABELS "${Labels}"
)
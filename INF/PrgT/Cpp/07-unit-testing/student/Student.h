#ifndef INC_07_UNIT_TESTING_STUDENT_H
#define INC_07_UNIT_TESTING_STUDENT_H
#include <string>

class Student
{
private:
    std::string name;
    std::string course;
    std::string emoji;
    double sleepDuration;
    int enrolmentNumber;

public:
    Student();
    Student(std::string name, std::string studiengang);
    void visitLecture();
    double solveAssignment(int number);
    static std::string askQuestion();
    static bool solveDifficultAssignment(std::string s);
    void sleep(double hours);
    void exmatriculate(int enrollmentNumber);
    std::string getEmoji();
    [[nodiscard]] double getSleepDuration() const;
};

#endif //INC_07_UNIT_TESTING_STUDENT_H

#include <cmath>
#include <iostream>
#include <utility>
#include <boost/algorithm/string.hpp>
#include "Student.h"

static int studentCounter = 0;

Student::Student() {
    name = "";
    course = "";
    emoji = "";
    sleepDuration = 8;
    ++studentCounter;
    enrolmentNumber = studentCounter;
}

Student::Student(std::string name, std::string studiengang) {
    this->name = std::move(name);
    this->course = std::move(studiengang);
    emoji = "motivated";
    sleepDuration = 8;
    ++studentCounter;
    enrolmentNumber = studentCounter;
}

void Student::visitLecture() {
    emoji = "focused";
}

double Student::solveAssignment(int number) {
    sleepDuration -= 4;
    return std::sqrt(number);
}

std::string Student::askQuestion() {
    return "I have a question.";
}

bool Student::solveDifficultAssignment(std::string s) {

    for (size_t i = 0; i < s.length(); ++i) {
        if (!isalpha(s[i])) {
            s.erase(i);
            i > 0 ? --i : i = 0;
        }
    }
    boost::algorithm::to_lower(s);
    std::cout << s << std::endl;
    if (s == std::string(s.rbegin(), s.rend()))
        return true;

    return false;
}

void Student::sleep(double hours) {
    sleepDuration += (hours * 0.9);
}

void Student::exmatriculate(int enrollmentNumber) {
    if (enrollmentNumber == enrolmentNumber) {
        std::cout << "Student with enrolment number " << enrolmentNumber << "has been exmatriculated." << std::endl;
        --studentCounter;
        emoji = "done!";
        sleepDuration += 8;
    }
    else {
        std::cout << "Wrong student." << std::endl;
    }
}

std::string Student::getEmoji() {
    return emoji;
}

double Student::getSleepDuration() const {
    return sleepDuration;
}

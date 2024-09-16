#include <iostream>

#include "Matrix.h"

std::wstring getCell(int value) {
    auto result = L'│' + std::to_wstring(value);
    while (result.length() < 4) {
        result += ' ';
    }
    return result;
}

Matrix initMatrix(const size_t s1, const size_t s2) {
    auto result = Matrix(s1);
    for (auto& elem : result) {
        elem.assign(s2, -1);
    }
    return result;
}

void printMatrix(const Matrix& matrix) {
    auto answer = std::wstring(L"Полученная матрица:\n");
    auto s1 = matrix.size();
    auto s2 = matrix[0].size();
    answer += L"┌───";
    for (auto i = 0; i < s2; ++i) {
        answer += L"┬───";
    }
    answer += L"┐\n│   ";
    for (auto i = 0; i < s2; ++i) {
        answer += getCell(i);
    }
    answer += L"│\n";
    for (auto i = 0; i < s1; ++i) {
        answer += L"├───";
        for (auto j = 0; j < s2; ++j) {
            answer += L"┼───";
        }
        answer += L"┤\n";
        answer += getCell(i);
        for (auto j = 0; j < s2; ++j) {
            answer += getCell(matrix[i][j]);
        }
        answer += L"│\n";
    }
    answer += L"└───";
    for (auto j = 0; j < s2; ++j) {
        answer += L"┴───";
    }
    answer += L"┘\n";
    std::wcout << answer;
}

bool needPrintMatrix = false;

void setNeedPrintMatrix(const bool newValue) {
    needPrintMatrix = newValue;
}

bool isNeedPrintMatrix() {
    return needPrintMatrix;
}
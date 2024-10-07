#include "Matrix.h"

#include <iostream>

Matrix initMatrix(const int &row, const int &col) {
    auto result = Matrix(row);
    for (int i = 0; i < row; ++i) {
        result[i].resize(col);
    }
    return result;
}

Matrix generateMatrix(const int &row, const int &col) {
    auto matrix = initMatrix(row, col);
    for (int i = 0; i < row; ++i) {
        for (int j = 0; j < col; ++j) {
            matrix[i][j] = rand() % 1024;
        }
    }
    return matrix;
}

void readSizes(int &row, int &col) {
    std::wcout << L"Введите количество строк: ";
    std::wcin >> row;
    if (row < 1) {
        throw std::invalid_argument("Invalid matrix row number");
    }
    std::wcout << L"Введите количество столбцов: ";
    std::wcin >> col;
    if (col < 1) {
        throw std::invalid_argument("Invalid matrix column number");
    }
}

Matrix readMatrix(const int &row, const int &col) {
    auto matrix = initMatrix(row, col);
    for (int i = 0; i < row; ++i) {
        for (int j = 0; j < col; ++j) {
            std::wcout << L"Введите элемент на позиции (" << i << L", " << j << L"): ";
            std::wcin >> matrix[i][j];
        }
    }
    return matrix;
}

std::wstring getCell(const int &value) {
    auto result = L'│' + std::to_wstring(value);
    while (result.length() < 7) {
        result += ' ';
    }
    return result;
}

std::wostream &operator <<(std::wostream &os, const Matrix &matrix) {
    auto answer = std::wstring();
    const auto s1 = matrix.size();
    const auto s2 = matrix[0].size();
    answer += L"┌──────";
    for (auto i = 0; i < s2; ++i) {
        answer += L"┬──────";
    }
    answer += L"┐\n│      ";
    for (auto i = 0; i < s2; ++i) {
        answer += getCell(i);
    }
    answer += L"│\n";
    for (auto i = 0; i < s1; ++i) {
        answer += L"├──────";
        for (auto j = 0; j < s2; ++j) {
            answer += L"┼──────";
        }
        answer += L"┤\n";
        answer += getCell(i);
        for (auto j = 0; j < s2; ++j) {
            answer += getCell(matrix[i][j]);
        }
        answer += L"│\n";
    }
    answer += L"└──────";
    for (auto j = 0; j < s2; ++j) {
        answer += L"┴──────";
    }
    answer += L"┘\n";
    os << answer;
    return os;
}

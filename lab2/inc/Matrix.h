#ifndef MATRIX_H
#define MATRIX_H

#include <istream>
#include <vector>

typedef std::vector<std::vector<int> > Matrix;

Matrix initMatrix(const int &, const int &);

Matrix generateMatrix(const int &, const int &);

void readSizes(int &, int &);

Matrix readMatrix(const int &, const int &);

std::wostream &operator <<(std::wostream &, const Matrix &);

#endif //MATRIX_H

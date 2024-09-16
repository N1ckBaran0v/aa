#ifndef __ALGORITHM_H__
#define __ALGORITHM_H__

#include <string>

class Algorithm {
public:
    Algorithm(const std::wstring&, const std::wstring&);
    virtual ~Algorithm() = default;

    virtual int execute() = 0;

protected:
    const std::wstring first, second;
};

#endif // __ALGORITHM_H__
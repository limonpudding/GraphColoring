# cuprumtan 23.03.2019

# graph example
# https://pp.userapi.com/c853420/v853420175/bd2f/aZyi66Qlivk.jpg

from itertools import islice

# ab ac ad ba bc bd da db dc ea eb ec ed
#graph = [[0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0],  # ab
#         [0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0],  # ac
#         [0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0],  # ad
#         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],  # ba
#         [1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0],  # bc
#         [1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0],  # bd
#         [1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0],  # da
#         [0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0],  # db
#         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],  # dc
#         [1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],  # ea
#         [0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0],  # eb
#         [0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0],  # ec
#         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]  # ed

graph = [[]]
#with open('/home/cuprumtan/prog/greedy/test.txt', 'r') as f:
with open('~/GraphColoring/src/python/test.txt', 'r') as f:

    for line in islice(f, 2):
        graph = [[int(num) for num in line.split(' ')] for line in f]

# coloring
def greedyColoring():
    colored = [[]]
    colorNumber = 0
    stack = []
    temp_1 = temp_2 = -1

# recheck func
    def loop(num):
        for t in colored[colorNumber]:
            if graph[t][num] == 1:
                return False
        return True

    for a in graph:
        temp_1 += 1
        if temp_1 not in stack:
            if not loop(temp_1):
                colored.append([])
                colorNumber += 1
                stack.append(temp_1)
                colored[colorNumber].append(temp_1)
            temp_2 = temp_1
            for b in a[temp_1:]:
                if b == 0 and temp_2 not in stack and loop(temp_2):
                    stack.append(temp_2)
                    colored[colorNumber].append(temp_2)
                temp_2 += 1
    return colored

result = greedyColoring()

print ('Colored vertexes:')
for i in range(len(result)):
    print('color ' + str(i + 1) + ': ')
    for j in range(len(result[i])):
        print(str(result[i][j]))
    print()

print('Compact view:')
print (greedyColoring())
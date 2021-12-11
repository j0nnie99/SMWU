import time
start = time.time()

#입력 파일 읽어오기
f = open("homework2_input_file/homework2_input_file_2.txt", "r")
line = f.readlines()

##R, N, S(arrS) 분리해서 저장 
tmp = line[0]
tmp = tmp.replace("\n","")
tmp = tmp.replace("(","")
tmp = tmp.replace(")","")
guide = tmp.split(', ')
R = int(guide[0])
N = int(guide[1])
arrS = guide[2:]

#랭킹 스코어쌍을 리스트(score)로 저장 + 개행문자 제거
scores = line[1:]
for i in range(R):
    tmp = line[i+1]
    tmp = tmp.replace("\n","")
    scores[i] = tmp.split(' ')
    i = i + 1

#score[i][1]을 기준으로 내림차순 정렬
for i in range(R-1): #전체 범위 내림차순 정렬
    for j in range(i+1,R):
        if scores[i][1]<scores[j][1]:
            scores[i],scores[j] = scores[j],scores[i]

for i in range(len(arrS)): #arrS의 개수만큼 반복
    for j in range(N): #scores를 N개까지만 비교
        if arrS[i] < scores[j][1]:
            if arrS[i] < scores[N][1]: #N번째까지 비교할 때까지 N보다 큰 S의 값이 없을 경우(=순위권 안에 X) -1 return
                print("S_" + str(i+1) + ": -1")
                break
            else:
                j = j + 1
        elif arrS[i] == scores[j][1]:
            print( "S_" + str(i+1) + ": " + str(j+1) + "등")
            break
        elif arrS[i] > scores[j][1]:
            print( "S_" + str(i+1) + ": " + str(j+1) + "등")
            break

f.close

print("수행 시간: ", time.time() - start)
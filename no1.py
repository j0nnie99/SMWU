import time

start = time.time()
f = open("homework2_input_file/homework2_input_file_1.txt", "r") #입력 파일 읽어오기
line = f.readlines() #줄마다의 요소들을 line 리스트에 넣기
N = int(line[0]) #첫 줄은 점의 개수(N)

#arr(리스트)에 line리스트의 값을 리스트로 변환하여 넣기
arr = ['0'] * (N + 1)
for i in range(N):
    tmp = line[i+1]
    tmp = tmp.replace("\n","")
    arr[i] = tmp.split(' ')
    arr[i][0] = int(arr[i][0]) #문자열에서는 음수값 비교가 정상적으로 되지 않으므로 형 변환
    arr[i][1] = int(arr[i][1])
    i = i + 1

for i in range(N-1): #i는 0부터 n-1까지
    for j in range(i+1,N): #j는 1부터 n까지
        if arr[i][1]>arr[j][1]: #y좌표 비교 후, 작은 게 앞으로
            arr[i],arr[j]=arr[j],arr[i]
        elif arr[i][1]==arr[j][1]:  #y좌표가 같다면 x좌표 비교
            if arr[i][0]>arr[j][0]:
                arr[i],arr[j]=arr[j],arr[i]

for i in range(N):
    print(arr[i][0],arr[i][1])

print("수행 시간: ", time.time() - start)

f.close 


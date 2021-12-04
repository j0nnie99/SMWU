import pymysql

#db연결에 대한 정보를 class로 묶기
class db_con:
    host = "localhost"
    user = "root"
    password = "wjddls0307"
    db = "likelion"
    char = "utf8"

if __name__=='__main__':
    conn = pymysql.connect(host=db_con.host, user=db_con.user, password=db_con.password, db = db_con.db, charset=db_con.char, autocommit=True)
    curs = conn.cursor(pymysql.cursors.DictCursor)


    while(True):
        print("\n\n")
        print("-------- 과제 메뉴 --------\n")
        print("|  1. 과제 제출하기       |\n")
        print("|  2. 과제 수정하기       |\n")    
        print("|  3. 과제 삭제하기       |\n")
        print("|  4. 과제 목록           |\n")                
        print("-------- 회원 정보 --------\n")
        print("|  5. 9기 명단 보기       |\n")
        print("|  6. 운영진 명단 보기    |\n")
        print("|  7. 해커톤 팀 확인하기  |\n")
        print("-------- EXIT(0) --------\n")

        num = int(input("사용할 메뉴를 선택하세요: "))

        if (num == 0):  #프로그램 종료
            break

        elif (num == 1):    #과제 제출 메뉴
            print("\n\n")
            print("-------- 과제 제출하기 --------\n")
            w = input("주차: ")
            num = input("회원 번호: ")
            link = input("깃헙 링크: ")
            sql = """insert into ASG(Week, MeNum, Link) values (%s, %s, %s)"""
            curs.execute(sql,(w, num, link))
            conn.commit()
            print("\n")
            print("과제가 제출되었습니다!")
            conn.close()
            continue

        elif (num == 2):       #과제 수정
            print("\n\n")
            print("-------- 과제 수정하기 --------\n")
            w = input("주차: ")
            num = input("회원 번호: ")
            link = input("깃헙 링크: ")
            sql = """update ASG set Link='{}'where Week='{}' and MeNum='{}'""".format(link, w, num)
            curs.execute(sql)
            conn.commit()

            print("\n")
            print("과제가 수정되었습니다!")
            conn.close()
            continue

        elif (num == 3):    #과제 삭제
            print("\n\n")
            print("-------- 과제 삭제하기 --------\n")
            w = input("주차: ")
            num = input("회원 번호: ")
            sql = "delete from ASG where week='{}' and MeNum='{}'".format(w, num)
            curs.execute(sql)
            conn.commit()

            print("\n")
            print("과제가 삭제되었습니다!")
            conn.close()
            continue

        elif (num == 4):    #과제 목록
            print("\n\n")
            print("[과제 목록]\n")
            sql2 = "select * from Members, ASG where Members.MeNum = ASG.MeNum"
            curs.execute(sql2)
            list = curs.fetchall()
            for l in list:
                week = l['Week']
                url = l['Link']
                name = l['MeName']
                print('-------------------------------------------------\n')
                print("[" + str(week) + "주차] " + url + " | " + name + "\n")
            continue

        elif (num == 5):    #9기 회원 명단 확인 메뉴
            print("\n\n")
            print("**2021 Like Lion 9기 명단**\n")
            members = "select * from Members, Team where Members.TNum = Team.TNum"
            curs.execute(members)
            member = curs.fetchall()
            for m in member:
                name = m['MeName']
                major = m['Major']
                ID = m['ID']
                Team = m['TName']
                print(name + "_" + major + "(" + str(ID) + ") / " + Team + "팀")
            continue

        elif (num == 6):    #운영진 명단 확인 메뉴
            print("\n\n")
            print("**2021 Like Lion 운영진 명단**\n")
            managers = "select * from Managers"
            curs.execute(managers)
            manager = curs.fetchall()
            for m in manager:
                name = m['MaName']
                major = m['Major']
                ID = m['ID']
                work = m['work']
                print(name + "_" + major + "(" + str(ID) + ") / 담당: " + str(work))
            continue
                
        elif (num == 7):    #해커톤 팀 명단 확인 메뉴
            print("\n\n")
            print("**2021 해커톤 팀 명단**\n")
            teams = "select * from Team, Managers where Team.Manager = Managers.MaNum"
            curs.execute(teams)
            team = curs.fetchall()
            for t in team:
                num = t['TNum']
                name = t['TName']
                info = t['Info']
                manager = t['MaName']
                print("[" + str(num)+ "] " + name + "\n-> 설명: " + info + "\n-> 담당 운영진: " + manager + "\n")
            continue

        

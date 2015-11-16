f=open("MaDum3.csv","r")
out=open("resTests.txt","w")
for line in f:
        commands=line.split(";")
        num=int(commands[0].lstrip("S"))
        out.write("/**\n*Attribut: ")
        if(num<25):
                out.write("size\n")
        else:
                out.write("start\n")
        out.write("*sequence: ")
        for cmd in commands[1:-2]:
                cmd2=cmd.rstrip('\n')
                if cmd2:
                        out.write(cmd2+", ")
        out.write("\n**/\n")
        out.write("@Test(expected="+commands[-1].rstrip('\n')+".class)\npublic void test"+commands[0]+"()throws Exception{\n")
        out.write("\tMyList list = new "+commands[1]+";\n")
        for cmd in commands[2:-2]:
                cmd2=cmd.rstrip('\n')
                if cmd2:
                        out.write("\tlist."+cmd2+";\n")

        out.write("}\n\n")
f.close()
out.close()

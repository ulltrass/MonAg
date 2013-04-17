On Error resume next

Set objShell = CreateObject("WScript.shell") 
Set objEnv = objShell.Environment("Process") 
Set objFSO = CreateObject("Scripting.FileSystemObject") 
Set objNetwork = CreateObject("WScript.Network") 

Const MBCONVERSION= 1024000
Const GBCONVERSION= 1024000000

strUserName = objEnv("USERNAME") 
strComputerName = lcase(objEnv("COMPUTERNAME"))
bPath = "."     'To state Server & path to save data


strComputer = "."

Dim Stuff, myFSO, WriteStuff, dateStamp

Set myFSO = CreateObject("Scripting.FileSystemObject")

'To use default computer name to make it unique
Set WriteStuff = myFSO.OpenTextFile(bPath & "\disk_usage_" & strComputerName & ".csv", 2, True) ' 2 = writing


'==============

strComputer = "."
Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colItems = objWMIService.ExecQuery("Select * from Win32_Volume")
For each objItem in colItems

tmp = tmp & "Drive Letter " & "," & objItem.DriveLetter & vbCrLf
tmp = tmp & "Capacity Space (GB) " & "," & (objItem.Capacity/GBCONVERSION) & vbCrLf
tmp = tmp & "Free Space (GB) " & "," & (objItem.FreeSpace/GBCONVERSION) & vbCrLf
tmp = tmp & "File System " & "," & objItem.FileSystem & vbCrLf & vbCrLf

Next


objTextFile.WriteLine Now & " == All Map Drives on system shown below ==v" & scriptVersion
 
'Loop through current network drives
Set colDrives = objNetwork.EnumNetworkDrives
For i = 0 to colDrives.Count-1 Step 2
 objTextFile.WriteLine colDrives.Item(i) & vbTab & colDrives.Item (i + 1)
Next
 
Set objShell = Nothing
Set objFSO = Nothing
Set objNetwork = Nothing


WriteStuff.WriteLine(tmp)

WriteStuff.Close


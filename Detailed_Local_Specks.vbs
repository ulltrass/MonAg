On Error resume next

Set objShell = CreateObject("WScript.shell") 
Set objEnv = objShell.Environment("Process") 
Set objFSO = CreateObject("Scripting.FileSystemObject") 
Set objNetwork = CreateObject("WScript.Network") 

Const MBCONVERSION= 1048576
Const GBCONVERSION= 1073741824

strUserName = objEnv("USERNAME") 
strComputerName = lcase(objEnv("COMPUTERNAME"))
bPath = "."     'To state Server & path to save data


strComputer = "."

Dim Stuff, myFSO, WriteStuff, dateStamp

Set myFSO = CreateObject("Scripting.FileSystemObject")

'To use default computer name to make it unique
Set WriteStuff = myFSO.OpenTextFile(bPath & "\" & strComputerName &" "& strUserName & ".csv", 2, True) ' 2 = writing


'==============

Set objItem = CreateObject("WScript.Network") 
If err.Number <> 0 Then 'If error occured then display notice
    Wscript.Echo "Error create Network object" & err.number, err.description
    err.Clear
End if

    tmp = tmp & "User Name" & "," & objItem.UserName & vbCrLf
    tmp = tmp & "Domain Name" & "," & objItem.UserDomain & vbCrLf

'==============


Set SystemSet = GetObject("winmgmts:").InstancesOf ("Win32_OperatingSystem")  
dim tmp
for each System in SystemSet

tmp = tmp & "OS Name " & "," & System.Caption & vbCrLf

next

'==============

Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colSettings = objWMIService.ExecQuery("Select * from Win32_OperatingSystem")
For Each objOperatingSystem in colSettings 

    tmp = tmp & "OS Version " & "," & objOperatingSystem.Version & vbCrLf
    tmp = tmp & "Service Pack " & "," &_
        objOperatingSystem.ServicePackMajorVersion _
        & "." & objOperatingSystem.ServicePackMinorVersion & vbCrLf

Next


Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colItems = objWMIService.ExecQuery("Select * from Win32_ComputerSystem",,48) 
For Each objItem in colItems

'Computer Name
tmp = tmp & "Computer Name " & "," & objItem.Name & vbCrLf
tmp = tmp & "Model " & "," & objItem.Model & vbCrLf

Next


Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colItems = objWMIService.ExecQuery("Select * from Win32_Processor")
For Each objItem in colItems

'CPU
    tmp = tmp & "CPU " & "," & objItem.Name & vbCrLf

Next


Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colBIOS = objWMIService.ExecQuery("Select * from Win32_BIOS")
For each objBIOS in colBIOS

'   tmp = tmp & "Installable Languages " & "," & objBIOS.InstallableLanguages & vbCrLf
'   tmp = tmp & "Manufacturer " & "," & objBIOS.Manufacturer & vbCrLf
'   tmp = tmp & "" & "," & objBIOS.Name & vbCrLf
'   tmp = tmp & "Release Date " & "," & objBIOS.ReleaseDate & vbCrLf
    tmp = tmp & "Serial Number / Service Tag " & "," & objBIOS.SerialNumber & vbCrLf
'   tmp = tmp & "SMBIOS Version " & "," & objBIOS.SMBIOSBIOSVersion & vbCrLf
  
Next


Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colItems = objWMIService.ExecQuery("Select * from Win32_PhysicalMemory")
For Each objItem in colItems

'   tmp = tmp & "Name " & "," & objItem.Name & vbCrLf
'   tmp = tmp & "RAM Capacity " & "," & (objItem.Capacity/MBCONVERSION) & "MB" & vbCrLf
    tmp = tmp & "RAM Capacity (MB)" & "," & (objItem.Capacity/MBCONVERSION) & vbCrLf

Next


'strComputer = "."
Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colItems = objWMIService.ExecQuery("Select * from Win32_CDROMDrive")
For Each objItem in colItems
    tmp = tmp & "CD\DVD Drive " & "," & objItem.Name & vbCrLf
'   tmp = tmp & "Description " & objItem.Description & vbCrLf
'   tmp = tmp & "Device  " & objItem.DeviceID & vbCrLf

Next

'==============
strQuery = "SELECT * FROM Win32_NetworkAdapterConfiguration WHERE MACAddress > ''"

Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colItems = objWMIService.ExecQuery( strQuery, "WQL", 48 )

For Each objItem In colItems
If IsArray( objItem.IPAddress ) Then
If UBound( objItem.IPAddress ) = 0 Then
tmp = tmp & "IP Address " & objItem.IPAddress(0)
Else

tmp = tmp & "IPV4 " & "," & Join( objItem.IPAddress, ", IPV6 ," ) & vbCrLf

End If
End If

Next


Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colItems = objWMIService.ExecQuery("Select * From Win32_NetworkAdapterConfiguration Where IPEnabled = True")

For Each objItem in colItems
tmp = tmp & "MAC Address " & "," & objItem.MACAddress & vbCrLf & vbCrLf

Next

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


Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colItems = objWMIService.ExecQuery("Select * from Win32_LogicalDisk")
For each objItem in colItems
 
  tmp = tmp & "Volume " & "," & objItem.Name & vbCrLf
  tmp = tmp & "Serial Number " & "," & objItem.VolumeSerialNumber & vbCrLf

'Add & vbCrLf at the back to have additional spacing
'  tmp = tmp & "Serial Number " & "," & objItem.VolumeSerialNumber & vbCrLf & vbCrLf

Next


Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colItems = objWMIService.ExecQuery("Select * from Win32_LogicalDisk")
For Each objDisk in colItems

    tmp = tmp & "Device "& "," & objDisk.DeviceID
    Select Case objDisk.DriveType

        Case 1
            tmp = tmp & vbCrLf & "No root directory Drive type could not be " & "determined"
        Case 2
            tmp = tmp & vbCrLf & "DriveType " & "," & "Removable Drive" & vbCrLf
        Case 3 
            tmp = tmp & vbCrLf & "DriveType " & "," & "Local Hard Disk" & vbCrLf
        Case 4
            tmp = tmp & vbCrLf & "DriveType " & "," & "Network Disk" & vbCrLf
        Case 5
            tmp = tmp & vbCrLf & "DriveType " & "," & "Compact Disk" & vbCrLf
        Case 6
            tmp = tmp & vbCrLf & "DriveType " & "," & "RAM Disk" & vbCrLf
        Case Else
            tmp = tmp & vbCrLf & "Drive type could not be determined" & vbCrLf
    End Select
Next

'Add & vbCrLf at the back to have additional spacing
'        Case Else
'            tmp = tmp & vbCrLf & "Drive type could not be determined" & vbCrLf & vbCrLf
'    End Select
'Next

 
Set objShell = Nothing
Set objFSO = Nothing
Set objNetwork = Nothing


WriteStuff.WriteLine(tmp)

WriteStuff.Close

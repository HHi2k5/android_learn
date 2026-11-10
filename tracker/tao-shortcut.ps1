# Tao icon (tracker/app.ico) va loi tat "Lo trinh Android" tren Desktop.
# Chay:  powershell -ExecutionPolicy Bypass -File tracker\tao-shortcut.ps1

$ErrorActionPreference = 'Stop'
$here = Split-Path -Parent $MyInvocation.MyCommand.Path
$node = (Get-Command node).Source
$ico = Join-Path $here 'app.ico'

# --- icon: o vuong bo goc mau xanh + dau tick trang ---
Add-Type -AssemblyName System.Drawing
$size = 256
$bmp = New-Object System.Drawing.Bitmap $size, $size
$g = [System.Drawing.Graphics]::FromImage($bmp)
$g.SmoothingMode = 'AntiAlias'
$g.Clear([System.Drawing.Color]::Transparent)
$r = 56; $d = $r * 2; $m = 8; $w = $size - 2 * $m
$path = New-Object System.Drawing.Drawing2D.GraphicsPath
$path.AddArc($m, $m, $d, $d, 180, 90)
$path.AddArc($m + $w - $d, $m, $d, $d, 270, 90)
$path.AddArc($m + $w - $d, $m + $w - $d, $d, $d, 0, 90)
$path.AddArc($m, $m + $w - $d, $d, $d, 90, 90)
$path.CloseFigure()
$g.FillPath((New-Object System.Drawing.SolidBrush ([System.Drawing.Color]::FromArgb(11, 122, 92))), $path)
$pen = New-Object System.Drawing.Pen ([System.Drawing.Color]::White), 30
$pen.StartCap = 'Round'; $pen.EndCap = 'Round'; $pen.LineJoin = 'Round'
$pts = @(
  (New-Object System.Drawing.PointF 70, 134),
  (New-Object System.Drawing.PointF 112, 176),
  (New-Object System.Drawing.PointF 188, 86)
)
$g.DrawLines($pen, [System.Drawing.PointF[]]$pts)
$g.Dispose()

$png = New-Object System.IO.MemoryStream
$bmp.Save($png, [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
$bytes = $png.ToArray()
$out = New-Object System.IO.MemoryStream
$bw = New-Object System.IO.BinaryWriter $out
$bw.Write([uint16]0); $bw.Write([uint16]1); $bw.Write([uint16]1)   # ICONDIR
$bw.Write([byte]0); $bw.Write([byte]0); $bw.Write([byte]0); $bw.Write([byte]0)  # 256x256
$bw.Write([uint16]1); $bw.Write([uint16]32)
$bw.Write([uint32]$bytes.Length); $bw.Write([uint32]22)
$bw.Write($bytes)
$bw.Flush()
[System.IO.File]::WriteAllBytes($ico, $out.ToArray())

# --- loi tat ---
$lnk = Join-Path ([Environment]::GetFolderPath('Desktop')) 'Lo trinh Android.lnk'
$s = (New-Object -ComObject WScript.Shell).CreateShortcut($lnk)
$s.TargetPath = $node
$s.Arguments = '"' + (Join-Path $here 'app.js') + '"'
$s.WorkingDirectory = $here
$s.IconLocation = "$ico,0"
$s.WindowStyle = 7   # thu nho: khong hien cua so console
$s.Description = 'Lo trinh Android -> Automotive'
$s.Save()
Write-Output "Da tao: $lnk"

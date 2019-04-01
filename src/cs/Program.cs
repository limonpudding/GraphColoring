using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace АнализСложности
{
    class Program
    {
        static bool Verify(int[,] mat, int[] vertex)
        {
            int currow = 1;
            int rownum = vertex.Length;
            for (int i = 0; i < rownum; i++)
            {
                for (int j = currow; j < rownum; j++)
                {
                    if ((mat[i, j] == 1) && (vertex[i] == vertex[j]))
                        return true;
                }
                currow++;
            }
            return false;
        }
        static void Main(string[] args)
        {
            int n;
            int[,] mat;
            try
            {
                StreamReader reader = new StreamReader("matrix.txt");
                n = Convert.ToInt32(reader.ReadLine());
                Console.WriteLine(n);
                mat = new int[n, n];
                for (int i = 0; i < n; i++)
                {
                    for (int j = 0; j < n; j++)
                    {
                        int r = reader.Read() - 48;
                        Console.Write(r + " ");
                        mat[i, j] = r;
                        reader.Read();
                    }
                    reader.ReadLine();
                    Console.WriteLine();
                }
            }
            catch
            {
                Console.WriteLine("Ошибка чтения файла");
                return;
            }            
            bool notfound = true;
            int[] vertex = new int[n];
            for (int i = 0; i < n; i++)
            {
                vertex[i] = 1;
            }
            notfound = Verify(mat, vertex);
            int CurVer = n - 1;
            int k;
            for (k = 2; (k < n) && (notfound); k++)
            {
                bool notall = true;
                CurVer = n - 1;
                do
                {
                    if (CurVer == -1)
                        notall = false;
                    else
                    {
                        if (vertex[CurVer] != k)
                        {
                            vertex[CurVer]++;
                            CurVer = n - 1;
                            notfound = Verify(mat, vertex);
                        }
                        else
                        {
                            vertex[CurVer] = 1;
                            CurVer--;
                        }
                    }
                }
                while (notall && notfound);
            }
            Console.WriteLine("Хроматическое число = " + (k-1));
            for (int j = 0; j < n; j++)
                Console.Write(" " + vertex[j]);
            Console.WriteLine();
            Console.ReadKey();
        }
    }
}

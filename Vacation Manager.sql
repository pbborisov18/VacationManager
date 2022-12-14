USE [master]
GO
/****** Object:  Database [Vacation Manager]    Script Date: 20/11/2022 17:22:47 ******/
CREATE DATABASE [Vacation Manager]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Vacation Manager', FILENAME = N'D:\SQL\MSSQL15.SQLEXPRESS01\MSSQL\DATA\Vacation Manager.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Vacation Manager_log', FILENAME = N'D:\SQL\MSSQL15.SQLEXPRESS01\MSSQL\DATA\Vacation Manager_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Vacation Manager] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Vacation Manager].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Vacation Manager] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Vacation Manager] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Vacation Manager] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Vacation Manager] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Vacation Manager] SET ARITHABORT OFF 
GO
ALTER DATABASE [Vacation Manager] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Vacation Manager] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Vacation Manager] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Vacation Manager] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Vacation Manager] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Vacation Manager] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Vacation Manager] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Vacation Manager] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Vacation Manager] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Vacation Manager] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Vacation Manager] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Vacation Manager] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Vacation Manager] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Vacation Manager] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Vacation Manager] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Vacation Manager] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Vacation Manager] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Vacation Manager] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Vacation Manager] SET  MULTI_USER 
GO
ALTER DATABASE [Vacation Manager] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Vacation Manager] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Vacation Manager] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Vacation Manager] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Vacation Manager] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Vacation Manager] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Vacation Manager] SET QUERY_STORE = OFF
GO
USE [Vacation Manager]
GO
/****** Object:  Table [dbo].[Leaves]    Script Date: 20/11/2022 17:22:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Leaves](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[StartDate] [datetime2](7) NOT NULL,
	[EndDate] [datetime2](7) NOT NULL,
	[CreationDate] [datetime2](7) NOT NULL,
	[HalfDay] [bit] NOT NULL,
	[Approved] [bit] NOT NULL,
	[UserId] [int] NOT NULL,
 CONSTRAINT [PK_Leaves] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Projects]    Script Date: 20/11/2022 17:22:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Projects](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](250) NOT NULL,
 CONSTRAINT [PK_Projects] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 20/11/2022 17:22:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Teams]    Script Date: 20/11/2022 17:22:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teams](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[ProjectId] [int] NULL,
 CONSTRAINT [PK_Teams] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 20/11/2022 17:22:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](200) NOT NULL,
	[FirstName] [nvarchar](50) NOT NULL,
	[LastName] [nvarchar](50) NOT NULL,
	[RoleId] [int] NOT NULL,
	[TeamId] [int] NOT NULL,
	[IsLeadDev] [bit] NOT NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Projects] ON 

INSERT [dbo].[Projects] ([Id], [Name], [Description]) VALUES (1, N'Unassigned', N'Unassigned')
SET IDENTITY_INSERT [dbo].[Projects] OFF
GO
SET IDENTITY_INSERT [dbo].[Roles] ON 

INSERT [dbo].[Roles] ([Id], [Name]) VALUES (1, N'Developer')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (2, N'TeamLead')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (3, N'CEO')
SET IDENTITY_INSERT [dbo].[Roles] OFF
GO
SET IDENTITY_INSERT [dbo].[Teams] ON 

INSERT [dbo].[Teams] ([Id], [Name], [ProjectId]) VALUES (1, N'Unassigned', 1)
SET IDENTITY_INSERT [dbo].[Teams] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([Id], [Username], [Password], [FirstName], [LastName], [RoleId], [TeamId], [IsLeadDev]) VALUES (1, N'CEO', N'$2a$10$6i5DHEdppPyUx1NMxWZ.5OUsPLvsQASYcaJFMl/cLe7MuWk9ojrtC', N'Ivan', N'Mitkov', 3, 1, 0)
INSERT [dbo].[Users] ([Id], [Username], [Password], [FirstName], [LastName], [RoleId], [TeamId], [IsLeadDev]) VALUES (2, N'TeamLead', N'$2a$10$nAnSQ3GRioVmwHEaYT2udesBQFvA825PIUQnvP/PppGSgPI0FM7By', N'Ivan', N'Mitkov', 2, 1, 1)
INSERT [dbo].[Users] ([Id], [Username], [Password], [FirstName], [LastName], [RoleId], [TeamId], [IsLeadDev]) VALUES (3, N'Dev', N'$2a$10$fd.X2agmCipGuXZupJ2qTeomHUZfEzFjX6PXDZzySbZfadgoP81LC', N'Ivan', N'Mitkov', 1, 1, 0)
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
ALTER TABLE [dbo].[Leaves]  WITH CHECK ADD  CONSTRAINT [FK_Leaves_Users] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Leaves] CHECK CONSTRAINT [FK_Leaves_Users]
GO
ALTER TABLE [dbo].[Teams]  WITH CHECK ADD  CONSTRAINT [FK_Teams_Projects] FOREIGN KEY([ProjectId])
REFERENCES [dbo].[Projects] ([Id])
GO
ALTER TABLE [dbo].[Teams] CHECK CONSTRAINT [FK_Teams_Projects]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Roles] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Roles] ([Id])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Roles]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Teams] FOREIGN KEY([TeamId])
REFERENCES [dbo].[Teams] ([Id])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Teams]
GO
USE [master]
GO
ALTER DATABASE [Vacation Manager] SET  READ_WRITE 
GO

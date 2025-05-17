import React from 'react';
import {useNavigate, useParams} from "react-router";
import useHostDetails from "../../../../hooks/useHostDetails.js";
import {
    Box,
    Button,
    Chip,
    CircularProgress,
    Grid,
    Typography,
    Paper,
    Avatar,
    Stack,
    Rating,
    Breadcrumbs,
    Link
} from "@mui/material";
import {
    ArrowBack,
    Factory,
    Star,
    FavoriteBorder,
    Share, Category
} from "@mui/icons-material";

const HostDetails = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const {host, country} = useHostDetails(id);

    if (!host || !country) {
        return (
            <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh'}}>
                <CircularProgress/>
            </Box>
        );
    }

    return (
        <Box>
            <Breadcrumbs aria-label="breadcrumb" sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/hosts");
                    }}
                >
                    Hosts
                </Link>
                <Typography color="text.primary">{host.name} {host.surname}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{p: 4, borderRadius: 4}}>
                <Grid container spacing={4}>
                    <Grid size={{xs: 12, md: 3}}>
                        <Box sx={{
                            display: 'flex',
                            justifyContent: 'center',
                            mb: 4,
                            bgcolor: 'background.paper',
                            p: 3,
                            borderRadius: 3,
                            boxShadow: 1
                        }}>
                            <Avatar
                                src={host.image || "/placeholder-host.jpg"}
                                variant="rounded"
                                sx={{
                                    width: '100%',
                                    height: 'auto',
                                    objectFit: 'contain'
                                }}
                            />
                        </Box>
                    </Grid>
                    <Grid size={{xs: 12, md: 9}}>
                        <Box sx={{mb: 3}}>
                            <Typography variant="h3" gutterBottom sx={{fontWeight: 600}}>
                                {host.name}
                            </Typography>

                            <Stack direction="row" spacing={1} alignItems="center" sx={{mb: 2}}>
                                <Rating
                                    value={host.rating || 4.5}
                                    precision={0.5}
                                    readOnly
                                    emptyIcon={<Star style={{opacity: 0.55}} fontSize="inherit"/>}
                                />
                                <Typography variant="body2" color="text.secondary">
                                    ({host.reviewCount || '12'} reviews)
                                </Typography>
                            </Stack>

                            <Stack direction="row" spacing={1} sx={{mb: 3}}>
                                <Chip
                                    icon={<Factory/>}
                                    label={country.name}
                                    color="secondary"
                                    variant="outlined"
                                    sx={{p: 2}}
                                />
                            </Stack>
                        </Box>
                    </Grid>
                    <Grid size={12} display="flex" justifyContent="space-between">
                        <Stack direction="row" spacing={2}>
                            <Button
                                variant="outlined"
                                color="secondary"
                                startIcon={<FavoriteBorder/>}
                            >
                                Wishlist
                            </Button>
                            <Button
                                variant="outlined"
                                startIcon={<Share/>}
                            >
                                Share
                            </Button>
                        </Stack>
                        <Button
                            variant="outlined"
                            startIcon={<ArrowBack/>}
                            onClick={() => navigate("/hosts")}
                        >
                            Back to Hosts
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Box>
    );
};

export default HostDetails;
